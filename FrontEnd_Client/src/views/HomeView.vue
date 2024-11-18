<script setup>
import {onMounted, onUnmounted, ref} from "vue";
import {useUserStore} from "../stores/user.js";
import {useCounterStore} from '../stores/counter'

const userStore = useUserStore();
const counterStore = useCounterStore()

const createResult = ref('');
const usersInfo = ref('');

const createUserData = ref({
      name: '',
      email: '',
      password: ''
    }
);

const messages = ref();
let eventSource = null;
const BASE_URL = import.meta.env.VITE_BASE_URL;

onMounted(() => {
  eventSource = new EventSource(`${BASE_URL}/time`);

  eventSource.onmessage = event => {
    messages.value = event.data;
  };

  eventSource.onerror = error => {
    console.error('SSE error:', error);
    eventSource.close();
  };
});

onUnmounted(() => {
  if (eventSource) eventSource.close();
});


const createUser = async () => {
  await userStore.createUser(createUserData.value);
  createResult.value = userStore.testResult;
}

const incrementCount = () => {
  counterStore.increment()
}

const getUsers = async () => {
  await userStore.getUsers();
  usersInfo.value = userStore.usersInfo;
}



</script>

<template>
  <div class="container">

    <div class="testBox">
      <form class="flex-box">
        <input v-model="createUserData.name" placeholder="userName"/>
        <input v-model="createUserData.email" placeholder="userEmail"/>
        <input v-model="createUserData.password" placeholder="userPassword"/>
        <button @click.prevent="createUser">Sign Up</button>
        <button @click.prevent="getUsers">Get Users</button>
      </form>
    </div>

    <div class="testBox">
      <h1>Counter View</h1>
      <p>Count: {{ counterStore.count }}</p>
      <p>Double Count: {{ counterStore.doubleCount }}</p>
      <button @click="incrementCount">Increment</button>
    </div>

    <div class="testBox">

      <h1> Server-Sent Event Example</h1>
      <div v-if="messages">
            {{ messages }}
      </div>
      <span v-for="user in usersInfo" class="flex-box">
      {{ user.name }}
      </span>
    </div>

  </div>
</template>

<style>

.container {
  display: flex;
  width: 80vw;
  height: 80vh;
  padding: 30px;
  background-color: wheat;
  border-radius: 20px;
}

.flex-box {
  width: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.testBox {
  width: 300px;
  height: 200px;
  background-color: #accfac;
  padding: 20px;
  border-radius: 20px;
  margin: 10px;
}
</style>