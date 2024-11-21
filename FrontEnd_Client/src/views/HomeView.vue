<script setup>
import {onMounted, onUnmounted, ref} from "vue";
import {useUserStore} from "../stores/user.js";

const userStore = useUserStore();

const createResult = ref('');
const usersInfo = ref('');

const createUserData = ref({
  name: '',
  email: '',
  password: ''
});
const loginUserData1 = ref({
  num: 1,
  email: '',
  password: ''
});

const loginUserData2 = ref({
  num: 2,
  email: '',
  password: ''
});

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
const login1 = async () => {

  await userStore.login(loginUserData1.value);

}
const login2 = async () => {

  await userStore.login(loginUserData2.value);

}
const logout = async () => {
}
const getUsers = async () => {
  await userStore.getUsers();
  usersInfo.value = userStore.usersInfo;
}


</script>

<template>

  <!--  SSE 테스트 -->
  <div v-if="messages">{{ messages }}</div>

  <!--  로그인 1번 -->
  <div class="container">
    <div class="testBox">
      <form class="flex-box">
        <h3>Login 1</h3>
        <input v-model="loginUserData1.email" placeholder="userEmail"/>
        <input v-model="loginUserData1.password" placeholder="userPassword"/>
        <button @click.prevent="login1">Login</button>
        <button @click.prevent="getUsers">Logout</button>
        <h2> {{ createResult.name }} </h2>
      </form>
    </div>

    <!--  로그인 2번 -->
    <div class="testBox">
      <form class="flex-box">
        <h3>Login 2</h3>
        <input v-model="loginUserData2.email" placeholder="userEmail"/>
        <input v-model="loginUserData2.password" placeholder="userPassword"/>
        <button @click.prevent="login2">Login</button>
        <button @click.prevent="getUsers">Logout</button>
        <h2> {{ createResult.name }} </h2>
      </form>
    </div>

    <div class="testBox">
      <span v-for="user in usersInfo" class="flex-box">
      {{ user.name }}
      </span>
    </div>

    <div class="testBox">
      <form class="flex-box">
        <h3>CreateUser</h3>
        <input v-model="createUserData.name" placeholder="userName"/>
        <input v-model="createUserData.email" placeholder="userEmail"/>
        <input v-model="createUserData.password" placeholder="userPassword"/>
        <button @click.prevent="createUser">Sign Up</button>
        <button @click.prevent="getUsers">Get Users</button>
        <h2> {{ createResult.name }} </h2>
      </form>
    </div>

  </div>
</template>

<style>

.container {
  display: flex;
  flex-wrap: wrap;
  width: 90vw;
  padding: 30px;
  background-color: wheat;
  border-radius: 20px;
  gap: 10px;
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
  height: 300px;
  background-color: #accfac;
  padding: 10px;
  border-radius: 20px;
  margin: 10px;
}
</style>