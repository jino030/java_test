// app.js
import myHeader from './componenets/myHeader.js'
import myTodoList from './componenets/myTodoList.js';
import myTodoAdd from './componenets/myTodoAdd.js';


const template = `
<div>
  <my-header v-on:data="getData"
             v-on:todo-add="todoAdd"></my-header>
  <my-todo-list v-if="listOK"
                v-bind:list="dataArray.todo"
                v-on:todo-add="todoAdd"
                v-on:todo-delete="todoDelete"
                v-on:todo-checked="todoChecked"></my-todo-list>
  <my-todo-add v-if="addOK"
               v-on:todo-add-event="todoAddEvent"></my-todo-add>
</div>
`;
new Vue({
  el: '#app',
  template: template,
  data: {
    listOK: false,
    addOK: false,
    dataArray: {}
  },
  components: {
    'my-header' : myHeader,
    'my-todo-list': myTodoList,
    'my-todo-add': myTodoAdd
  },
  methods: {
    todoAdd(){
      this.listOK = false;
      this.addOK = true;
    },
    todoList(){
      this.listOK = true;
      this.addOK = false;
    },
    getData(jsonData){
      console.log(jsonData);
      this.dataArray = jsonData;
      this.todoList();
    },
    todoDelete(no){
      this.dataArray.todo.forEach((el, idx) => {
        if(el.no == no){
          this.dataArray.todo.splice(idx,1);
        }
      });
    },
    todoChecked(no){
      console.log(no);
      this.dataArray.todo.forEach((el, idx) => {
        if(el.no == no) {
          console.log(el);
          if(el.isChecked == true) {
            el.isChecked = false;
          } else {
            el.isChecked = true;
          }
        }
      });
    },
    todoAddEvent(content, date, isChecked) {
      let no = 1;

      if(content != '' && date != ''){
        if (this.dataArray.todo.length != 0) {
          let idx = this.dataArray.todo.length - 1;
          no = Number(this.dataArray.todo[idx].no) + 1;
        }
  
        let todo = {no, content, date, isChecked};
        this.dataArray.todo.push(todo);
        this.todoList();
      } else {
        alert('내용을 입력해야지..');
      }
    }
  }
})
