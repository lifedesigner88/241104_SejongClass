const BASE_URL = import.meta.env.VITE_BASE_URL;

import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useUserStore = defineStore('user', () => {

    const testResult = ref(null)
    const createUser = async (createUserData) => {
        try {
            const response = await fetch(`${BASE_URL}/user`, {
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

    return {
        testResult,
        createUser
    }
})