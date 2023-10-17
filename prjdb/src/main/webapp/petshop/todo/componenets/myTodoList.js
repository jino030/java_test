// myTodoList.js

export default {
  template: `
  <div>
    <ul id="myUL">
      <li v-for="item in list" v-on:click="checked(item.no)" v-bind:class="{checked:item.isChecked}">
        <span >
          {{item.no}}. {{item.content}} ({{item.date}})
        </span>
        <span class="close" v-on:click.stop="todoDelete(item.no)">x</span>
      </li>
    </ul>
  </div>
  `,
  props: ['list'],
  methods: {
    todoDelete(no){
      this.$emit('todo-delete', no);
    },
    checked(no){
      console.log(no);
      this.$emit('todo-checked', no);
    }
  }
}