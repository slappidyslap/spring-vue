import axios from "@/plugins/axios";

class AuthService {

    async signIn({login, password}) {

        try {
            const response = await axios.post("/api/auth/sign-in", {
                login,
                password
            });

            localStorage.setItem(
                'authUserData', JSON.stringify(response.data));

            return await Promise.resolve(response.data);

        } catch (error) {
            console.error(error);

            return await Promise.reject(error);
        }
    } 

    logout() {
        localStorage.removeItem('authUserData');
    }

    async signUp({username, email, password, fullname}) {
        try {
            const response = await axios.post('/api/auth/sign-up', {
                username,
                email,
                password,
                fullname
            });

            return await Promise.resolve(response.data);
        } catch (error) {
            console.error(error);

            return await Promise.reject(error);
        }
    }

    getAuthorizationHeader() {
        const authUserData = localStorage.getItem('authUserData');

        console.log(authUserData);

        return `Bearer ${authUserData.accessToken}`;
    }

    getRefreshToken() {
        const authUserData = JSON.parse(localStorage.getItem('authUserData'));
        
        return authUserData?.refreshToken;
    }

    getAccessToken() {
        const authUserData = JSON.parse(localStorage.getItem('authUserData'));
        
        return authUserData?.accessToken;
    }

    updateAccessToken(refreshTokenResponseData) {
        let authUserData = JSON.parse(localStorage.getItem('authUserData'));
        authUserData.accessToken = refreshTokenResponseData.accessToken;
        authUserData.refreshToken = refreshTokenResponseData.refreshToken; 

        localStorage.setItem('authUserData', JSON.stringify(authUserData));
    }

    getAuthUserData() {
        return JSON.parse(localStorage.getItem('authUserData'));
    }

    setAuthUserDate(newAuthUserData) {
        console.log(newAuthUserData);

        localStorage.setItem('getAuthUserDate', newAuthUserData);
    }

    removeAuthUserData() {
        localStorage.removeItem('getAuthUserDate');
    }
}

export default new AuthService();