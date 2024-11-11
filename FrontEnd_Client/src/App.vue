<script setup>
import {RouterLink, RouterView} from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import {ref} from "vue";


const createUserData = ref({
      name: '',
      email: '',
      password: ''
    }
);
const baseUrl = import.meta.env.VITE_API_URL;
const createResult = ref('');




const createUser = async () => {

  const response = await fetch(`${baseUrl}/user`, {
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
  <header class="redBorder">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    <form>
      <input v-model="createUserData.name" placeholder="userName"/>
      <input v-model="createUserData.email" placeholder="userEmail"/>
      <input v-model="createUserData.password" placeholder="userPassword"/>
      <button @click.prevent="createUser">Sign Up</button>
    </form>
  </header>

  <RouterView/>
</template>

<style scoped>

.redBorder {
  border: 2px solid red;
  display: flex;
  flex-direction: column;
  place-items: center;
  padding: 2rem;

}

header {
  line-height: 1.5;
  max-height: 100vh;
}


nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
