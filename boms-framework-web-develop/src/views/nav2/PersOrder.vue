<template>
    <section>


    </section>
</template>

<script>
    import util from '../../common/js/util';


    export default {

        number: 'number',
        data() {
            return {
                filters: {
                    number: ''
                },
                form_user: {
                    username: '',
                    card: '',
                    birthday: '',
                    age: '',
                    sex: '',
                    adress:'',
                },
                form_fight: {
                    number: '',
                    capacity: '',
                    people: '',
                    starttime: '',
                    endtime: '',
                    time:'',
                    remark:''
                },

                tableKey: 0,
                list:null,
                total:null,
                listLoading: true,
                //列表查询
                listQuery: {
                    page: 1,
                    limit: 20,
                    name: undefined
                },

                dialogStatus: '',
                dialogFormVisible:false,
                viewOrderId:''

            }
        },

        //请求后端数据
        created() {
            this.getParams() //接收参数
        },

        //方法
        methods:{


        },//methods的结束

    }//export default的结束

</script>

<style scoped>

</style>