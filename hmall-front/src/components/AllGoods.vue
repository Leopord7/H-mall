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
                <div v-for="(item, i) in goods" :key=i class="per_good">item</div>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                
                activeIndex: "allGoods",

                goods: [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
            }
        },
        methods: {
            handleSelect(key, keyPath) {
            
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
    /* div.per_good:hover {
        transform: translateY(-3px);
        box-shadow: 1px 1px 20px #999;
        
    } */
</style>