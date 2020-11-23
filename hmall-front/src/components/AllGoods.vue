<template>
    <div class="allGoods">
        <div class="nav">
            <el-menu
                :default-active="activeIndex"
                class="el-menu-demo"
                mode="horizontal"
                @select="handleSelect"
                active-text-color="red"
                style="margin-right: 700px; bottom: -1px;">
                <el-menu-item index="allGoods">全部商品</el-menu-item>
            </el-menu>
        </div>
        <div class="goods_container">
            <div class="goods">
                <div v-for="(item, i) in goods.slice((currentPage - 1) * pageSize, currentPage * pageSize)" :key=i class="per_good">
                    <div>
                        {{ item.title }}
                    </div>
                    <div>
                        {{ item.price }}
                    </div>
                    <div>
                        <el-button type="primary" @click="goodDetail(i)">详情</el-button>
                    </div>
                </div>
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[5, 10]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="goods.length">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                
                activeIndex: "allGoods",

                goods: [],

                pageSize: 5,
                currentPage: 1,
            }
        },
        beforeCreate() {
            let _this = this
            this.$axios({
                method: "get",
                url: "/product/list",
            }) .then(res => {
                if (res.data.code != 200) {
                    alert("错误！" + res.data.message)
                } else {
                    _this.goods = res.data.data
                }
            })
        },
        methods: {
            handleSelect(key, keyPath) {
            
            },
            goodDetail(id) {
                let product = this.goods[(this.currentPage - 1) * this.pageSize + id]
                this.$router.push({path: 'goodDetail/' + product.id})
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
            },
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage
            }
        }
    }
    </script>
    
<style>
    .allGoods {
       
        flex: 1;
    }
    .nav {
        display: flex;
        justify-content: center;
        background-color: white;
        height: 11%;
        width: 100%;
    } 
    .goods_container {
        background: #ededed;
        width: 100%;    
        display: flex;
        justify-content: center;
        height: 89%;
        padding-top: 5px;
        padding-bottom: 10px;
    }
    .goods {
        width: 70%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }
    .per_good {
        width: 19.6%;
        height: 250px;
        transition: all .5s;
        background-color: white;
        border: 1px solid #efefef;
    }
    div.per_good:hover {
        transform: translateY(-3px);
        box-shadow: 1px 1px 20px #999;
        
    }
</style>