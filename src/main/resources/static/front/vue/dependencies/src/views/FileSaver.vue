<template>
  <div>
    <h3>
      <a href="https://github.com/eligrey/FileSaver.js" target="_blank">FileSalver</a>
    </h3>
    <h3>
      <a href="https://eligrey.com/demos/FileSaver.js/" target="_blank">FileSalver Demo</a>
    </h3>
    <div>
      <button @click="save">save</button>
    </div>
  </div>

</template>

<script>
import {saveAs} from 'file-saver';

export default {
  name: "FileSaver",
  methods: {
    save () {
      const blob = new Blob(["Hello, world"], {type: "text/plain;charset=utf-8"});
      saveAs(blob, "hello world.txt");
    }
  }
}
</script>

<style scoped>

</style>