const BASE_URL = import.meta.env.VITE_BASE_URL;

import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useUserStore = defineStore('user', () => {

    const testResult = ref(null)
    const usersInfo = ref();
    const createUser = async (createUserData) => {
        try {
            const response = await fetch(`${BASE_URL}/user/create`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(createUserData)
            })
            testResult.value = await response.json();
        } catch (e) {
            console.log(e);
        }
    }

    const getUsers = async () => {
        try {
            const response = await fetch(`${BASE_URL}/user/list`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            usersInfo.value = await response.json();
        } catch (e) {
            console.log(e);
        }
    }

    return {
        testResult,
        usersInfo,
        createUser,
        getUsers,
    }
})