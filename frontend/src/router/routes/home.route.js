import Index from '../../components/home/Index'
import Contact from '../../components/home/Contact'
import Message from "../../components/home/Message";
import Home from "../../components/home/Home";

export default [
  {
    path: '/',
    component: Index,
    children: [
      {
        name: 'home',
        path: 'home',
        component: Home,
      },
      {
        name: 'message',
        path: 'message',
        component: Message,
      },
      {
        name: 'contact',
        path: 'contact',
        component: Contact,
      },
    ]
  }
]
