
function minBalCheck(bal, request, recentTxnCount) {
  console.log('minimum Balance wothdrawal check')
  return request <= bal;
}

function perPeriodCheck(bal, request, recentTxnCount) {
  console.log('perPeriod withdrawal check');
  return recentTxnCount < 5;
}

accountProto = {
  withdrawPermission: minBalCheck,
  withdraw: function (amount) {
    console.log(`Asking to withdraw ${amount} current balance is ${this.balance}`);
    if (this.withdrawPermission(this.balance, amount, this.recentTxnCount)) {
      console.log('permitted!');
      this.balance -= amount;
      this.recentTxnCount++;
    } else {
      console.log('refused')
    }
  }
}

account = {
  __proto__: accountProto,
  balance: 100,
  recentTxnCount: 3,
};

console.dir(account);
account.withdraw(100_000);
console.log('------------------')
account.withdraw(10);
console.log('------------------')
account.withdraw(10);
console.dir(account);
console.log('------------------')
account.withdrawPermission = perPeriodCheck
account.withdraw(10);
console.dir(account);
