import axios from "@/plugins/axios";

class UserService {

    async getByUsername(username) {
        try {
            const response = await axios.get(`/api/users/${username}`);

            return await Promise.resolve(response.data);
        } catch (error) {
            console.error(error);

            return await Promise.reject(error);
        }
        
    }

    async uploadUserPic(file, username, onUploadProgress) {

        const formData = new FormData();
        formData.append("file", file);
        
        try {
            const response = await axios.post(`/api/users/${username}/user-pic`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                },
                onUploadProgress
            });

            return await Promise.resolve(response.data);

        } catch (error) {
            console.error(error);

            return await Promise.reject(error);
        }
    }
}

export default new UserService();