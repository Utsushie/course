import axios from 'axios'
import Vue from 'vue'
import request from '../../utils/request.js'

//删除课程
export const deleteChapter = params => request.get('/business/admin/chapter/deleteChapterss',params);