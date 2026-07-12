const js = require('@eslint/js')
const globals = require('globals')
const html = require('eslint-plugin-html')
const jquery = require('eslint-plugin-jquery')
const vue = require('eslint-plugin-vue')
const react = require('eslint-plugin-react')
const prettier = require('eslint-config-prettier/flat')

const allVueRulesOff = Object.fromEntries(
  Object.keys(vue.rules).map((name) => [`vue/${name}`, 'off']),
)

module.exports = [
  {
    ignores: ['**/node_modules/**', '**/target/**', '**/dist/**', '**/lib/**'],
  },
  js.configs.recommended,
  ...vue.configs['flat/recommended'],
  react.configs.flat.recommended,
  {
    languageOptions: {
      ecmaVersion: 'latest',
      sourceType: 'module',
      parserOptions: { ecmaFeatures: { jsx: true } },
      globals: {
        ...globals.browser,
        ...globals.node,
        ...globals.jquery,
        // uni-app
        uni: 'readonly',
        getCurrentPages: 'readonly',
        // AMD/UMD/CMD
        define: 'readonly',
        seajs: 'readonly',
      },
    },
    plugins: {
      html,
      jquery,
    },
    rules: {
      // 'no-console': 'warn',
      'no-unused-vars': 'warn',
      'no-empty': ['error', { allowEmptyCatch: true }],
    },
  },
  {
    // 测试文件：注入 Jest 全局
    files: ['**/*.{test,spec}.{js,jsx,ts,tsx}', '**/__tests__/**'],
    languageOptions: {
      globals: {
        ...globals.jest,
      },
    },
  },
  {
    files: ['**/src/main/resources/static/front/**/*.vue'],
    rules: {
      'vue/multi-word-component-names': 'off',
    },
  },
  {
    // 此 uni-app 项目为 Vue 2，而 eslint-plugin-vue v10 仅含 Vue 3 规则集，
    // 且 easycom 等机制与标准 Vue 差异较大，故关闭全部 vue/* 规则
    files: ['**/src/main/resources/static/front/uni-app/**'],
    rules: {
      ...allVueRulesOff,
    },
  },
  // 放在最后关闭所有与 Prettier 冲突的 ESLint 格式规则
  prettier,
]
