<template>
    <Login v-if="loginState === false"> </Login>
    <el-form
        :label-position="labelPosition"
        label-width="120px"
        style="max-width: 460px"
        v-else
    >
        <el-form-item label="Name">
            <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="Age">
            <el-input v-model="form.age" />
        </el-form-item>
        <el-form-item label="Time (ms)">
            <el-input v-model="form.timeInMs" />
        </el-form-item>
        <el-radio-group v-model="form.gender">
            <el-radio :value="'MALE'">Male</el-radio>
            <el-radio :value="'FEMALE'">Female</el-radio>
            <el-radio :value="'OTHER'">Other</el-radio>
        </el-radio-group>
        <br />
        <el-checkbox v-model="form.completed"> Completed </el-checkbox>
        <br />
        <el-button type="primary" @click="submitForm">Submit</el-button>
    </el-form>
</template>

<script lang="ts" setup>
import axios from 'axios'

import { ElMessage } from 'element-plus'
import { ref } from 'vue'
import Login from '@/components/Login.vue'
const labelPosition = ref('top')

const loginState = ref(false)
const form = ref({} as LapData)

const submitForm = async () => {
    const token = sessionStorage.getItem('token')
    try {
        const payload = form.value
        await axios.post(`http://localhost:8080/admin/insert`, payload, {
            headers: {
                Authorization: `Bearer: ${token}`
            }
        })
        ElMessage({
            message: 'Data successfully inserted',
            type: 'success'
        })
    } catch (e: any) {
        ElMessage({
            message: e.response.data,
            type: 'error'
        })
        console.log(e)
    }
}

const checkToken = () => {
    const token = sessionStorage.getItem('token')
    if (token != null) {
        loginState.value = true
    }
}

setTimeout(() => {
    checkToken(), 2000
})

interface LapData {
    name: string
    timeInMs: number
    gender: string
    age: number
    completed: boolean
}
</script>

<style scoped>
button {
    width: 100px;
}
</style>
