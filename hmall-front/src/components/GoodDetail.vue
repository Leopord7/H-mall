<template>
    <div class="good">
        <div class="good-container">
            <div class="good_img">

            </div>
            <div class="good_info">
                <div class="title">
                    <h4>{{ title }}</h4>
                </div>
                <div class="price">
                    <span class="price_info">
                        <em style="font-style: normal;">¥</em> {{ price }}
                    </span>
                </div>
                <div class="title" v-if="promotion">
                    <h5>{{ promotionTitle }}</h5>
                </div>

                <div class="price" v-if="promotion">
                    <span class="price_info">
                        当前秒杀价：<em>¥</em> <i>{{ promotionPrice }}</i>
                    </span>
                </div>
                <div class="tool">
                    <el-input-number v-model="num" @change="handleNumChange" :min="1" :max="10" label="数量">                       
                    </el-input-number>
                </div>
                <div class="tool">
                    <el-button type="primary" @click="buy" size="small">购买</el-button>
                </div>
            </div>
        <!--产品信息-->
      </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            id: "",
            title: "",
            price: "",
            promotion: false,
            promotionTitle: "",
            promotionPrice: "",
            sales: "",
            stock: "",

            num: 1,
        }
    },
    beforeCreate() {
        this.productId = this.$route.params.id;
        let _this = this
        this.$axios({
            url: '/product/detail/' + _this.productId,
            method: 'get',
        }) .then(res => {
            if (res.data.code != 200) {
                alert("错误！" + res.data.message)
            } else {
                let data = res.data.data
                _this.id = data.id
                _this.title = data.title
                _this.price = data.price
                _this.promotion = (data.promotionStatus != 0)
                _this.promotionTitle = data.promotionTitle
                _this.promotionPrice = data.promotionPrice
                _this.sales = data.sales
                _this.stock = data.stock
            }
        }) .catch(error => {
            console.log(error)
        })
    },
    methods: {
        handleNumChange() {

        },
        buy() {
            var _this = this
            var params = {
                productId: this.id,
                amount: this.num
            }
            this.$axios({
                method: "get",
                url: "/order/create",
                data: params,
            }) .then(res => {
                console.log(res)
            }) .catch(error => {
                console.log(error)
            })
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
    .tool {
        width: 100%;
        height: 20%;
        display: flex;
        justify-content: center;
        align-items: center;
        /* flex-direction: column; */
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