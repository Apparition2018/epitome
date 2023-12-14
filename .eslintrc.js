// ESLint: https://eslint.org/docs/latest/
// Configuration Objects: https://eslint.org/docs/latest/use/configure/configuration-files-new#configuration-objects
module.exports = {
  // Language Options: https://eslint.org/docs/latest/use/configure/language-options
  env: {
    browser: true,
    node: true,
    es2024: true,
    jquery: true
  },
  parserOptions: {
    // The version of ECMAScript
    ecmaVersion: 'latest',
    ecmaFeatures: {
      jxs: true
    },
    // The type of JavaScript source code: ① 'script'; ② 'module'; ③ 'commonjs'
    sourceType: 'module'
  },
  extends: [
    // https://www.npmjs.com/package/eslint-config-standard
    'standard',
    // https://www.npmjs.com/package/eslint-config-standard-jsx
    'standard-jsx',
    // https://www.npmjs.com/package/eslint-plugin-jquery
    // 'plugin:jquery/deprecated',
    // https://www.npmjs.com/package/eslint-plugin-vue
    'plugin:vue/recommended'
  ],
  plugins: [
    // https://www.npmjs.com/package/eslint-plugin-html
    'html',
    'jquery'
  ],
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-unused-vars': 'warn'
  },
  overrides: [
    {
      env: {
        node: true
      },
      files: ['.eslintrc.{js,cjs}'],
      parserOptions: {
        sourceType: 'script'
      }
    }
  ]
}
