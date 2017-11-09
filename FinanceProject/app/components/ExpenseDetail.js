import React, {Component} from "react";

import {Text} from "react-native-elements";
import {TextInput, View} from "react-native";

export default class ExpenseDetail extends Component{
    //let currentItem = [{id: "0", name: "none", type: "none"}];
    render(){
        const {currentItem} = this.props.navigation.state.params.expense;
        //console.log(currentItem);

        return<View>
            <TextInput
                style={{height: 40, borderColor: 'red', borderWidth: 1}}
                value={this.props.navigation.state.params.expense.name}
                title={"Name"}

            />

        </View>
    }


}