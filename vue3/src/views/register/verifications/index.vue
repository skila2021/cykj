<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="绑定的手机号，用于验证与限流" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入绑定的手机号，用于验证与限流"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="6位数字验证码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入6位数字验证码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="验证码过期时间" prop="expiresAt">
        <el-date-picker clearable
          v-model="queryParams.expiresAt"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择验证码过期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否已被使用" prop="isUsed">
        <el-input
          v-model="queryParams.isUsed"
          placeholder="请输入是否已被使用"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="验证码生成时间" prop="createdAt">
        <el-date-picker clearable
          v-model="queryParams.createdAt"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择验证码生成时间">
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
          v-hasPermi="['register:verifications:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['register:verifications:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['register:verifications:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['register:verifications:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="verificationsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="验证码记录唯一标识，主键" align="center" prop="id" />
      <el-table-column label="绑定的手机号，用于验证与限流" align="center" prop="phone" />
      <el-table-column label="6位数字验证码" align="center" prop="code" />
      <el-table-column label="验证码过期时间" align="center" prop="expiresAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.expiresAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否已被使用" align="center" prop="isUsed" />
      <el-table-column label="验证码生成时间" align="center" prop="createdAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当日该手机号发送次数" align="center" prop="attemptCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['register:verifications:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['register:verifications:remove']">删除</el-button>
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
      <el-form ref="verificationsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="绑定的手机号，用于验证与限流" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入绑定的手机号，用于验证与限流" />
        </el-form-item>
        <el-form-item label="6位数字验证码" prop="code">
          <el-input v-model="form.code" placeholder="请输入6位数字验证码" />
        </el-form-item>
        <el-form-item label="验证码过期时间" prop="expiresAt">
          <el-date-picker clearable
            v-model="form.expiresAt"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择验证码过期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否已被使用" prop="isUsed">
          <el-input v-model="form.isUsed" placeholder="请输入是否已被使用" />
        </el-form-item>
        <el-form-item label="验证码生成时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择验证码生成时间">
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

<script setup name="Verifications">
import { listVerifications, getVerifications, delVerifications, addVerifications, updateVerifications } from "@/api/register/verifications"

const { proxy } = getCurrentInstance()

const verificationsList = ref([])
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
    phone: null,
    code: null,
    expiresAt: null,
    isUsed: null,
    createdAt: null,
    attemptCount: null
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询注册、成功列表 */
function getList() {
  loading.value = true
  listVerifications(queryParams.value).then(response => {
    verificationsList.value = response.rows
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
    phone: null,
    code: null,
    expiresAt: null,
    isUsed: null,
    createdAt: null,
    attemptCount: null
  }
  proxy.resetForm("verificationsRef")
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
  getVerifications(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改注册、成功"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["verificationsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateVerifications(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addVerifications(form.value).then(response => {
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
    return delVerifications(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('register/verifications/export', {
    ...queryParams.value
  }, `verifications_${new Date().getTime()}.xlsx`)
}

getList()
</script>
