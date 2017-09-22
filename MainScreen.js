import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';
import QRCode from 'react-native-qrcode';

export default class MainScreen extends React.Component {
  static navigationOptions = { title: 'Main' };
  
  render() {
    return (
      <View style={styles.container}>
        <Text style={{margin:5}}>Sample app with native module - v0.03</Text>
        <View style={{flexDirection:'row', margin:5}}>
          <Button style={{margin:5}} title=" Logon " onPress={ () => { AppAuthAndroid.login() }} /> 
          <Button style={{margin:5}} title=" Logoff " onPress={ () => { AppAuthAndroid.logout() }} /> 
        </View>
        <QRCode value='This is a test' size={200} bgColor='black' fgColor='white'/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
