import json from '../../../../json.json';
class ZamenaService {

    getByGroup(group) {
        return this.get()[group];
    }

    get() {
        return json;
    }
}

export default new ZamenaService();
