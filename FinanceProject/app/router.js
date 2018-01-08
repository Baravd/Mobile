import React from 'react';

import ExpenseList from './components/ExpenseList'
import {StackNavigator} from 'react-navigation'

import Home from "./components/Home";
import ExpenseDetail from "./components/ExpenseDetail";
import {Mail} from "./components/Mail";
import {AddExpenseView} from "./components/AddExpenseView";
import {Statistics} from "./components/Statistics";
import {Register} from "./login/Register";
import { Login} from "./login/Login";

const Stack = StackNavigator({
    Login:{
        screen: Login
    },
    Home: {
        screen: Home,
    },
    Details: {
        screen: ExpenseDetail,
        path: 'details/:id',
    },
    List: {
        screen: ExpenseList,
    },
    Mail: {
        screen: Mail,
    },
    AddExpenseView: {
        screen: AddExpenseView,
    },
    Statistics: {
        screen: Statistics,
    },
    Register :{
        screen: Register,
    }

});

export default Stack;