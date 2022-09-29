import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import FontAwesomeIcon from '@/plugins/fontAwesome';
import uiComponents from '@/components/ui';
import interceptors from '@/plugins/axios/interceptors';

const app = createApp(App);

// TODO
app.component('font-awesome-icon', FontAwesomeIcon);

uiComponents.forEach(component => {
    app.component(component.name, component);
});

interceptors(store);

app.use(store).use(router).mount('#app');
