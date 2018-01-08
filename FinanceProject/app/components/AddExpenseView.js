import React, {Component} from "react";
import {TextInput, View, StyleSheet, Button, AsyncStorage} from "react-native";
import DatePicker from "react-native-datepicker";


export class AddExpenseView extends Component {
    constructor(props) {
        super(props);
        this.state = { nume: "Name", type: "Type", date: new Date(),id: '-1'};
        this.auth = global.firebaseApp.auth();
        this.auth.onAuthStateChanged((user)=>{
            if(user){
                this.dbRef = global.firebaseApp.database().ref().child('users').child(user.uid).child('expenses');
            }
        });
    }

    render() {

        return (<View>
            <TextInput
                style={styles.row}
                editable={true}
                keyboardType='numeric'

                onChangeText={(text) => this.setState({id: text})}
                value={this.state.id}
                maxLength={10}

            />
            <TextInput
                style={styles.row}
                editable={true}

                onChangeText={(text) => this.setState({nume: text})}
                value={this.state.nume}

            />
            <TextInput
                style={styles.row}
                editable={true}

                onChangeText={(text) => this.setState({type: text})}
                value={this.state.type}

            />
            <DatePicker date={this.state.date}
                        mode="date"
                        placeholder="purchase date"
                        onDateChange={(date) => {
                            this.setState({date: date})
                        }}

            />
            <Button
                title={"Save"}
                onPress={() => {
                    console.log(this.state.id, this.state.nume, this.state.type, this.state.date);

                    AsyncStorage.setItem(this.state.id, JSON.stringify({
                        id: this.state.id,
                        nume: this.state.nume,
                        type: this.state.type,
                        date: this.state.date
                    })).then(() => {
                        console.log("Add worked");
                        this.props.navigation.state.params.updateState();
                        console.log("It worked")
                        this.props.navigation.goBack();
                    });

                }}


            />

        </View>)
    }
}

const styles = StyleSheet.create({
    row: {
        marginBottom: 5,
        marginTop: 20,
        borderColor: '#E1B700'
    }
});
