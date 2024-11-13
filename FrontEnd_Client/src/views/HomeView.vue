<script setup>
import {ref} from "vue";
import {useUserStore} from "../stores/user.js";
import {useCounterStore} from '../stores/counter'

const userStore = useUserStore();
const counterStore = useCounterStore()


const createResult = ref('');

const createUserData = ref({
      name: '',
      email: '',
      password: ''
    }
);

const createUser = async () => {
  await userStore.createUser(createUserData.value);
  createResult.value = userStore.testResult;
}


const incrementCount = () => {
  counterStore.increment()
}

</script>

<template>
  <main>

    <form>
      <input v-model="createUserData.name" placeholder="userName"/>
      <input v-model="createUserData.email" placeholder="userEmail"/>
      <input v-model="createUserData.password" placeholder="userPassword"/>
      <button @click.prevent="createUser">Sign Up</button>
    </form>
    {{ createResult }}


    <div>
      <h1>Counter View</h1>
      <p>Count: {{ counterStore.count }}</p>
      <p>Double Count: {{ counterStore.doubleCount }}</p>
      <button @click="incrementCount">Increment</button>

    </div>

  </main>


</template>
