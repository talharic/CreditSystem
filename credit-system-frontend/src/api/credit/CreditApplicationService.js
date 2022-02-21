import axios from "axios";

class CreditApplicationService {
  saveCreditApplication(newCreditApplication) {
    var url = "api/v1/credit-applications";

    return axios.post(url, newCreditApplication);
  }

  showCreditApplication(nationalIdNumber) {
    var url = "api/v1/credit-applications/";

    return axios.get(url, {
      params: {
        id: nationalIdNumber
      },
    });
  }
}

export default new CreditApplicationService();