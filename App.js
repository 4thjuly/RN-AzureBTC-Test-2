import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import { StackNavigator } from 'react-navigation';
import MainScreen from './MainScreen';
import ScanScreen from './ScanScreen';
import QRScreen from './QRScreen';

export default SimpleApp = StackNavigator({
  Main: { screen: MainScreen },
  Scan: { screen: ScanScreen },
  QR: { screen: QRScreen }  
});
