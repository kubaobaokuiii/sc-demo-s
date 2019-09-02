import axios from 'axios';
import fetch from '../utils/fetch';

let base = '/api';

//查询所有航班信息
export function getFightListPage() {
    return fetch({
        url: '/api/flight/hangBan/allFight',
        method: 'get',
    });
}

//根据id查询飞机管理
export function getFightId() {
    return fetch({
        url: '/api/flight/hangBan/allFight/' + id,
        method: 'get',
    });
}



//增加飞机管理信息
export function addFight(params) {
    console.log("..................++++:" + JSON.stringify(params))
    // return fetch({
    //     url: '/api/flight/hangBan/addFight',
    //     method: 'post',
    //     body: JSON.stringify(params),
    //     headers:{
    //         'Content-Type': 'application/x-www-form-urlencoded',
    //         'Accept': 'application/json'
    //     }
    // });

    // let postData = {a:'b'};
    return fetch('/api/flight/hangBan/addFight', {
        method: 'POST',
        // mode: 'cors',
        // credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(params)
    })
}

//修改飞机管理信息
export function updateFight() {
    return fetch({
        url: '/api/flight/hangBan/updateFight',
        method: 'post',
    });
}

//删除id飞机管理信息
export function deleteFight() {
    return fetch({
        url: '/api/flight/hangBan/deleteFight/' + id,
        method: 'delete',
    });
}


