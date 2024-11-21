const BASE_URL = import.meta.env.VITE_BASE_URL;

import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useUserStore = defineStore('user', () => {

    const testResult = ref(null)
    const usersInfo = ref();
    const loginUser1 = ref();
    const loginUser2 = ref();


    // ⭐ 함수
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
            console.log(testResult.value);
        } catch (e) {
            console.log(e);
        }
    }

    const login = async (loginUserData) => {

        try {
            const response = await fetch(`${BASE_URL}/user/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginUserData)
            })

            if (loginUserData.id === 1) {
                loginUser1.value = await response.json();
                console.log(loginUser1.value);
            } else {
                loginUser2.value = await response.json();
                console.log(loginUser2.value);
            }

        } catch (e) {
            console.log(e, '로그인 실패입니다.');
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
        loginUser1,
        loginUser2,
        createUser,
        login,
        getUsers,
    }
})