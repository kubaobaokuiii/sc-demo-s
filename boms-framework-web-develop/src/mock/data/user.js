import Mock from 'mockjs';
const LoginUsers = [
  {
    id: 1,
    username: 'admin',
    password: '123456',
    avatar: 'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png',
    name: '张某某'
  }
];

const Users = [];

//数据模拟
// for (let i = 0; i < 86; i++) {
//   Users.push(Mock.mock({
//     id: Mock.Random.guid(),
//     name: Mock.Random.cname(),
//     addr: Mock.mock('@county(true)'),
//     'age|18-60': 1,
//     birth: Mock.Random.date(),
//     sex: Mock.Random.integer(0, 1)
//   }));
// }

// 数据测试
for (let i = 0; i < 86; i++) {
  Users.push(Mock.mock({
    id: '',
    username: '',
    adress: '',
    age:'',
    birthday: '',
    sex: 0,
    card:''
  }));
}

// //请求的数据
// let requestUrl = 'http://localhost:8087/api/user/user/allUser';
//
// fetch(requestUrl,{
//   method: 'get',
//   })
//     .then(res => {
//       return res.json()
//     })
//     .then(data => {
//       alert(data)
//       console.log("请求的数据最终为:............")
//       console.log(data);
//     })

// for (let i = 0; i < 86; i++) {
//   Users.push(Mock.mock({
//     id: '',
//     username: '',
//     adress: '',
//     age:'23',
//     birthday: '',
//     sex: '',
//     card:''
//   }));
// }

export { LoginUsers, Users };
