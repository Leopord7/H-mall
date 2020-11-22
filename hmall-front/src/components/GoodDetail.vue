<template>
    <div class="good">
        <div class="good-container">
            <div class="text">
                <span>
                    {{ title }}
                </span>
            </div>
            <div class="text">
                {{ price }}
            </div>
            <div class="text" v-if="promotion">
                {{ promotionTitle }}
            </div>
            <div class="text" v-if="promotion">
                {{ promotionPrice }}
            </div>
            <div class="text">
                <el-input-number v-model="num" @change="handleNumChange" :min="1" :max="10" label="数量"></el-input-number>
                <el-button type="primary" @click="buy">购买</el-button>
            </div>
        </div>
        <!--产品信息-->
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

            num: "",
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
                _this.promotionPrice = data.promotion_price
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

        }
    }
}
</script>

<style>
    .good {
       
    }
    .good-container {
        background: #ededed;
        min-height: 89%;
        width: 100%;
        display: flex;
        justify-content: center;
        flex-direction: column;
    }
    .text {
        width: 100%;
        height: 150px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>