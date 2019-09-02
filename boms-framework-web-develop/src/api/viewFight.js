import axios from 'axios';
import fetch from '../utils/fetch';

let base = '';

export function getViewFightListPage(params) {
    return fetch({
        url: '/api//order/selectFightIdByUserId/'+params,
        method: 'get',
    });
}
