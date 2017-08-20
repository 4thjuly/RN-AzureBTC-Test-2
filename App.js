import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';

export default class App extends React.Component {
  render() {
    AppAuthAndroid.configureService();
    return (
      <View style={styles.container}>
        <Text>Sample app with native module - v0.01</Text>
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
