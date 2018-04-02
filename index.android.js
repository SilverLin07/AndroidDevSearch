import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class RN_Demo extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          welcome to react Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.android.js
        </Text>
      </View>
    );
    }
}

var styles = StyleSheet.create({
  container:{
    flex:1,
    jsutifyContent:'center',
    alignItems:'center',
    backgroundColor:'#F5FcFF',
  },
  welcome:{
    fontSize:20,
    textAlign:'center',
    margin:10,
  },
  instructions:{
    textAlign:'center',
    color:'#333333',
    marginBottom:5,
  },
});

AppRegistry.registerComponent('RN_Demo', () => RN_Demo);