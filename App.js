import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';
import QRCode from 'react-native-qrcode';
import { StackNavigator } from 'react-navigation';
import MainScreen from './MainScreen';

// class HomeScreen extends React.Component {
//   static navigationOptions = { title: 'Welcome' };
//   render() {
//     return <Text>Hello, Navigation!</Text>;
//   }
// }

export default SimpleApp = StackNavigator({
  Main: { screen: MainScreen }
});
