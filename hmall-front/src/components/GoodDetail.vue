<template>
    <div class="good">
        <div class="good-container">
            <div class="good_img">

            </div>
            <div class="good_info">
                <div class="title">
                    <h4>{{ product.productName }}</h4>
                </div>
                <div class="price">
                    <span class="price_info">
                        <em style="font-style: normal;">¥</em>333
                    </span>
                </div>

                <div class="num">
                    <el-input-number v-model="num" @change="handleNumChange" :min="1" :max="10" label="数量">                       
                    </el-input-number>
                </div>
            </div>
        </div>
        <!--产品信息-->
      </div>
</template>

<script>
export default {
    data() {
        return {
            num: "",
            product: {
                productId: "",
                productName: "十万个为什么",
            },

        }
    },
    methods: {
        handleNumChange() {

        }
    }
}
</script>

<style>
    .good {
       height: 80%;
       display: flex;
       justify-content: center;
       background: #ededed;
    }
    .good-container {
        background: white;
        height: 89%;
        width: 80%;
        margin-top: 25px;
        border: 1px solid #f0f0f0;
        border: 1px solid rgba(0, 0, 0, .06);
        border-radius: 5px;
        display: flex;
        justify-content: center;
    }
    .good_img {
        height: 60%;
        width: 30%;
        align-self: center;
        border: black 1px solid;
        margin-right: 150px;
        margin-left: 70px;
    }
    .good_info {
        height: 50%;    
        width: 30%;
        align-self: center;
        border: black 1px solid;
    }
    .price {
        width: 100%;
        height: 30%;
    }
    .num {
        width: 100%;
        height: 30%;
        display: flex;
        justify-content: center;
    }
    .title {
        width: 100%;
        height: 25%;
        text-align: center;
    }
    h4 {
        font-size: 24px;
        line-height: 1.25;
        color: #000;
        font-weight: 400;
        margin: 0px;
        margin-top: 15px;
      }
    .price_info {
        display: block;
        color: #d44d44;
        font-weight: 600;
        font-size: 18px;
        line-height: 20px;
        text-align: right;
        padding-right: 30px;
    }
</style>