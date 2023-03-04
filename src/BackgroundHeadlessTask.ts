export const  BackgroundHeadlessTask = async() => {
  console.log('Hey this is a headless JS task');
  await fetch(`http://192.168.1.103:3000/`);
  return;
}
