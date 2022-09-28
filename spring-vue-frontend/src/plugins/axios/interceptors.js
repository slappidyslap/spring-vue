import authService from "@/services/auth.service";
import axiosInstance from "@/plugins/axios";
import router from "@/router";
import axios from "axios";

function isExists(object) {
    return Boolean(object);
}

const axiosRefreshTokenInstance = axios.create({
    baseURL: "http://localhost:8080/api/auth",
    headers: {
        "Content-Type": "application/json"
    }
});

// async function updateAccessTokenByRefreshToken(store) {
// }

export default function interceptors(store) {
    // Задает access token в хедер Authorization
    axiosInstance.interceptors.request.use(
        (config) => {

            const accessToken = authService.getAccessToken();
            if (isExists(accessToken)) {
                config.headers['Authorization'] = `Bearer ${accessToken}`;
            }
            return config;
        },
        (error) => {

            console.log('inter 1 error');
            console.error(error);
            return Promise.reject(error);
        }
    );
    // Обновляет access token при помощи 
    // refresh token если оно просрочен
    axiosInstance.interceptors.response.use(
        (response) => response,
        async (error) => {

            const config = error.config;
            if (!config.url.includes('/auth/sign-in') && error.response) {

                /* 
                В случае, если сервер продолжает возвращать код состояния 401, 
                он может перейти в цикл Infinite. 
                Мы используем флаг _retry в конфиге запроса (config). 
                _retry устанавливается на true сразу после того, 
                как мы впервые достигнем статуса 401. 
                */
                if (error.response.status === 401) {
                    try {
                        const response = await axiosRefreshTokenInstance.post("/refresh-token", {
                            refreshToken: authService.getRefreshToken(),
                        });
                    
                        store.dispatch('auth/refreshToken', response.data);
                    
                        console.log("Refresh token was used");

                        return await Promise.resolve(axiosInstance(config));
                    } catch (error) {
                        
                        console.log("refresh token is expired");
                        // Если refresh token тоже был исчерпан
                        if (error.response.status === 401) {
                            store.dispatch('auth/removeAuthData');
                            router.replace('login');
                        }
                        return await Promise.reject(error);
                    }
                }
            }
        }
    );
}

