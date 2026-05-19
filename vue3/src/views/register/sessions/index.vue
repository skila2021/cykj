<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入关联用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Token过期时间" prop="expiresAt">
        <el-date-picker clearable
          v-model="queryParams.expiresAt"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择Token过期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="会话创建时间" prop="createdAt">
        <el-date-picker clearable
          v-model="queryParams.createdAt"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择会话创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后访问时间" prop="lastAccessedAt">
        <el-date-picker clearable
          v-model="queryParams.lastAccessedAt"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择最后访问时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['register:sessions:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['register:sessions:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['register:sessions:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['register:sessions:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sessionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="会话唯一标识，主键" align="center" prop="id" />
      <el-table-column label="关联用户ID" align="center" prop="userId" />
      <el-table-column label="JWT或随机Token字符串，用于客户端鉴权" align="center" prop="token" />
      <el-table-column label="Token过期时间" align="center" prop="expiresAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.expiresAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会话创建时间" align="center" prop="createdAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后访问时间" align="center" prop="lastAccessedAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.lastAccessedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['register:sessions:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['register:sessions:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改注册、成功对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="sessionsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入关联用户ID" />
        </el-form-item>
        <el-form-item label="JWT或随机Token字符串，用于客户端鉴权" prop="token">
          <el-input v-model="form.token" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="Token过期时间" prop="expiresAt">
          <el-date-picker clearable
            v-model="form.expiresAt"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择Token过期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="会话创建时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择会话创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后访问时间" prop="lastAccessedAt">
          <el-date-picker clearable
            v-model="form.lastAccessedAt"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择最后访问时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Sessions">
import { listSessions, getSessions, delSessions, addSessions, updateSessions } from "@/api/register/sessions"

const { proxy } = getCurrentInstance()

const sessionsList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: null,
    token: null,
    expiresAt: null,
    createdAt: null,
    lastAccessedAt: null
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询注册、成功列表 */
function getList() {
  loading.value = true
  listSessions(queryParams.value).then(response => {
    sessionsList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    userId: null,
    token: null,
    expiresAt: null,
    createdAt: null,
    lastAccessedAt: null
  }
  proxy.resetForm("sessionsRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加注册、成功"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getSessions(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改注册、成功"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["sessionsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSessions(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addSessions(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除注册、成功编号为"' + _ids + '"的数据项？').then(function() {
    return delSessions(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('register/sessions/export', {
    ...queryParams.value
  }, `sessions_${new Date().getTime()}.xlsx`)
}

getList()
</script>
