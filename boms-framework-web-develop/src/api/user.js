import axios from 'axios';
import fetch from '../utils/fetch';

let base = '';

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };

export const getUserList = params => { return axios.get(`${base}/user/user/list`, { params: params }); };

//export const getUserListPage = params => { return axios.get(`/api/user/user/allUser`, { params: params }); };

export function getUserListPage() {
    return fetch({
        url: '/api/user/user/allUser',
        method: 'get',
    });
}
// params: params

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }); };