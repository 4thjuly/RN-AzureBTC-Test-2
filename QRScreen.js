import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';
import QRCode from 'react-native-qrcode';

export default class MainScreen extends React.Component {
  static navigationOptions = { title: 'QR' };
  
  render() {
    const text = 'This is a test';    
    return (
      <View style={styles.container}>
        <Text style={{margin:5}}> {text} </Text>
        <QRCode value={text} size={200} bgColor='darkblue' fgColor='lightblue'/>
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
