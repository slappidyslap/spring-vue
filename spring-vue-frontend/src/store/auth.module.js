import authService from "@/services/auth.service";

const authUserData = JSON.parse(
    localStorage.getItem('authUserData'));

const initState = authUserData ?
    {
        isLoggedIn: true,
        authUserData
    }
    :
    {
        isLoggedIn: false,
        authUserData: null
    };


export default {
    namespaced: true,

    state: initState,

    actions: {
        async signIn({ commit }, user) {
            try {
                const data = await authService.signIn(user);

                commit('setIsLoggedIn', true);
                commit('setAuthUserData', data);

                return await Promise.resolve(data);
            } catch (error) {

                commit('setIsLoggedIn', false);
                commit('setAuthUserData', null);

                return await Promise.reject(error);
            }
        },

        logout({ commit }) {
            authService.logout();
            commit('setIsLoggedIn', false);
            commit('setAuthUserData', null);
        },

        // TODO
        async signUp({ commit }, newUserData) {
            try {
                const data = await authService.signUp(newUserData);
                // TODO
                commit('setIsLoggedIn', false);

                return await Promise.resolve(data);
            } catch (error) {
                // TODO
                commit('setIsLoggedIn', false);

                return await Promise.reject(error);
            }
        },

        refreshToken({ commit }, refreshTokenResponseData) {
            
            authService.updateAccessToken(refreshTokenResponseData);
            commit('refreshToken', refreshTokenResponseData);
        },

        removeAuthData({ commit }) {

            authService.removeAuthUserData();
            commit('setIsLoggedIn', false);
            commit('setAuthUserData', null);
        },

        // getAuthenticatedUserUsername() {
        //     return authService.getAuthUserDate()?.authUserData.username;
        // }
    },
    mutations: {
        setIsLoggedIn(state, isLoggedIn) {
            state.isLoggedIn = isLoggedIn;
        },
        setAuthUserData(state, authUserData) {
            state.authUserData = authUserData;
        },
        refreshToken(state, refreshTokenResponseData) {
            state.isLoggedIn = true;
            state.authUserData = { ...state.authUserData, ...refreshTokenResponseData };
        }
    },
};