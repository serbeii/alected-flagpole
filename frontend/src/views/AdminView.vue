<template>
            <el-radio :value="''">None</el-radio>
            <template v-for="(value, index) in lapData" :key="index">
            </template>
        <el-button type="primary" @click="submitForm">Submit</el-button>
</template>

<script lang="ts" setup>
import axios from 'axios'

import { ElMessage } from 'element-plus'
import { onMounted, ref, reactive } from 'vue'

const lapData = ref({} as LapData)

const submitForm = async () => {
    const userId = sessionStorage.getItem('userId')
    try {
        const response = await axios.post(
            `http://localhost:8080/api/${userId}`,
        )
        ElMessage({
            message: response.data,
            type: 'success'
        })
    } catch (e: any) {
        ElMessage({
            message: e.response.data.message,
            type: 'error'
        })
        console.log(e)
    }
}

interface LapData {
    projectId: number
    area: string
    projectName: string
    projectDescription: string
    projectNeeds: string
}

</script>

<style scoped>
button {
    width: 100px;
}
</style>

