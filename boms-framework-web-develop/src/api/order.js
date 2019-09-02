import axios from 'axios';
import fetch from '../utils/fetch';

let base = '';

export function getOrderListPage() {
    return fetch({
        url: '/api/order/allOrders',
        method: 'get',
    });
}