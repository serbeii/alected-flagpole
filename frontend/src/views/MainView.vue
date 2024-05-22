<template>
    <h1>Rankings:</h1>
    <el-row class="receipts">
        <el-col :span="48">
            <div class="list-container">
                <div v-for="(value, index) in messages" :key="index">
                    <p>
                        <strong>{{ index + 1 }}.</strong>
                        <strong> Name: </strong>
                        {{ value.name }}
                        <strong>Time Taken:</strong>
                        {{ value.timeInMs }}
                        <strong v-if="value.completed == false">Disqualified</strong>
                    </p>
                </div>
            </div>
        </el-col>
    </el-row>
</template>

<script lang="ts" setup>
import axios from 'axios'

import { onMounted, ref } from 'vue'

const messages = ref([] as LapData[])

const fetchMessages = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/main/results`)
        const value = response.data as LapData[]
        value.sort((a, b) => {
            if (!a.completed && b.completed) {
                return 1 // Move a to the bottom
            } else if (a.completed && !b.completed) {
                return -1 // Move b to the bottom
            } else {
                return a.timeInMs - b.timeInMs
            }
        })
        messages.value = value
        console.log(messages.value)
    } catch (e: any) {
        console.log(e)
    }
}

onMounted(fetchMessages)
setInterval(fetchMessages, 5000)

interface LapData {
    name: string
    completed: boolean
    timeInMs: number
}
</script>

<style scoped>
.single-page {
    width: 2rem;
}

.receipts {
    max-height: 500px;
    overflow: auto;
}

.list-container {
    max-height: inherit;
    overflow-y: auto;
}
</style>
