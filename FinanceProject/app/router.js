import React from 'react';

import ExpenseList from './components/ExpenseList'
import {StackNavigator} from 'react-navigation'

import Home from "./components/Home";
import ExpenseDetail from "./components/ExpenseDetail";

const Stack = StackNavigator({
    Home: {
        screen: Home,
    },
    Details: {
        screen: ExpenseDetail,
        path: 'details/:id',
    },
    List:{
        screen: ExpenseList,
    },
   /* Propose: {
        screen: Propose,
    },*/
});

export default Stack;