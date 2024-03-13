import React, { useEffect } from "react";
import "chart.js";
import styled from "styled-components";
import { useGlobalContext } from "../../context/globalContext";
import History from "../../History/History";
import { InnerLayout } from "../../styles/Layouts";
import BarChart from "./BarChart.js";
import PieChart from "./PieChart.js";
import { dollar } from "../../utils/Icons";
import Chart from "../Chart/Chart";

function Transactions() {
  return (
    <TransactionsStyled>
      <InnerLayout>
        <h1>Analytics</h1>
        <div className="both-charts">
          <div className="bChart">
            <BarChart />
          </div>
          <div className="pChart">
            <PieChart />
          </div>
        </div>
      </InnerLayout>
    </TransactionsStyled>
  );
}

const TransactionsStyled = styled.div`
//   .both-charts {
//     display: flex;
//     flex-direction: column;
//     align-items: center;
//     justify-content: space-between;
//   }
  .bChart {
    text-align: center;
    margin: 96px;
  }
  .pChart {
    text-align: center;
    margin: 96px;
  }
`;

export default Transactions;
