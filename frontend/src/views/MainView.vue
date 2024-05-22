<template>
    <el-row class="receipts">
        <el-col :span="24">
            <div class="list-container">
                <div v-for="(value, index) in messages" :key="index">
                    <p>
                        <strong>{{ index }}</strong>
                    </p>
                    <p>
                        <strong>Name:</strong>
                        {{ value.name }}
                    </p>
                    <p>
                        <strong>Time Taken:</strong>
                        {{ value.timeInMs }}
                    </p>
                    <p v-if="value.completed == false">
                        <strong>Disqualified</strong>
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
        const response = await axios.get(
            `http://localhost:8080/main`
        )
        const value = response.data as LapData[]
        value.sort((a, b) => a.timeInMs - b.timeInMs)
        messages.value = value;
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
    padding-left: calc(var(--section-gap) / 4);
    max-height: 500px;
    overflow: auto;
}

.list-container {
    max-height: inherit;
    overflow-y: auto;
}
</style>
