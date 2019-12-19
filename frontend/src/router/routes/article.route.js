import Create from "../../components/article/Create.vue";
import List from "../../components/article/List.vue";
import Index from "../../components/article/Index.vue";
import Update from "../../components/article/Update.vue";
import Delete from "../../components/article/delete.vue";

export default [
  {
    path: '/article',
    component: Index,
    children: [
      {
        name: 'articleList',
        path: '',
        component: List,
      },
      {
        name: 'articleCreate',
        path: 'create',
        component: Create,
      },
      {
        name: 'articleUpdate',
        path: 'update/:id',
        component: Update,
      },
      {
        name: 'articleDelete',
        path: 'delete/:id',
        component: Delete,
      },
    ]
  }
]
