// myTodoAdd.js

export default {
  template: `
  <div>
    <div  id="myDIV" class="header">
      <h2 style="margin:5px">My To Do List</h2>
      <input v-model="content" type="text" id="myInput" placeholder="Title...">
      <input v-model="date" type="date" id="myInput" placeholder="Due Date...">
      <span v-on:click="todoAddEvent" class="addBtn">Add</span>
    </div>
  </div>
  `,
  data: function() {
    return {
      content: '',
      date: '',
      isChecked: false
    }
  },
  methods: {
    todoAddEvent(){
      this.$emit('todo-add-event', this.content, this.date, this.isChecked);
    }
  }
}