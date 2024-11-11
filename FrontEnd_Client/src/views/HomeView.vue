<script setup>
import {ref} from "vue";

const createUserData = ref({
      name: '',
      email: '',
      password: ''
    }
);

const BASE_URL = import.meta.env.VITE_API_URL;
const createResult = ref('');
const createUser = async () => {

  const response = await fetch(`${BASE_URL}/user`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(createUserData.value)
  });
  createResult.value = await response.json();

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


  </main>
</template>
