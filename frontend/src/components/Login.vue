<script setup lang="ts">
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { reactive } from 'vue'

const form = reactive({
    username: '',
    password: ''
})

const submitForm = async () => {
    try {
        const response = await axios.post(`http://localhost:8080/main/login`, form)
        sessionStorage.setItem('token', response.data)
        ElMessage({
            message: 'Login successful',
            type: 'success'
        })
    } catch (e: any) {
        ElMessage({
            message: 'err',
            type: 'error'
        })
        console.log(e)
    }
}
</script>

<template>
    <el-form-item label="Username">
        <el-input v-model="form.username" />
    </el-form-item>
    <el-form-item label="Password">
        <el-input v-model="form.password" autocomplete="off" type="password" />
    </el-form-item>
    <el-button type="primary" @click="submitForm">Login</el-button>
</template>
