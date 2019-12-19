import Create from "../../components/table/Create.vue";
import List from "../../components/table/List.vue";
import Index from "../../components/table/Index.vue";
import Update from "../../components/table/Update.vue";
import Delete from "../../components/table/delete.vue";

export default [
  {
    path: '/table',
    component: Index,
    children: [
      {
        name: 'tableList',
        path: '',
        component: List,
      },
      {
        name: 'tableCreate',
        path: 'create',
        component: Create,
      },
      {
        name: 'tableUpdate',
        path: 'update/:id',
        component: Update,
      },
      {
        name: 'tableDelete',
        path: 'delete/:id',
        component: Delete,
      },
    ]
  }
]
