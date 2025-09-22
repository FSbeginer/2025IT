namespace _2025광주_1과제.View
{
    partial class Form소개서추가
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.placeHolder1 = new _2025광주_1과제.Controls.PlaceHolder();
            this.placeHolder2 = new _2025광주_1과제.Controls.PlaceHolder();
            this.placeHolder3 = new _2025광주_1과제.Controls.PlaceHolder();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(21, 27);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(29, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "제목";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(21, 64);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(41, 12);
            this.label2.TabIndex = 1;
            this.label2.Text = "부제목";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(21, 107);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(29, 12);
            this.label3.TabIndex = 2;
            this.label3.Text = "내용";
            // 
            // placeHolder1
            // 
            this.placeHolder1.Location = new System.Drawing.Point(75, 22);
            this.placeHolder1.Msg = "";
            this.placeHolder1.Name = "placeHolder1";
            this.placeHolder1.Size = new System.Drawing.Size(231, 25);
            this.placeHolder1.TabIndex = 3;
            // 
            // placeHolder2
            // 
            this.placeHolder2.Location = new System.Drawing.Point(75, 107);
            this.placeHolder2.Msg = "";
            this.placeHolder2.Name = "placeHolder2";
            this.placeHolder2.Size = new System.Drawing.Size(231, 254);
            this.placeHolder2.TabIndex = 3;
            // 
            // placeHolder3
            // 
            this.placeHolder3.Location = new System.Drawing.Point(75, 61);
            this.placeHolder3.Msg = "";
            this.placeHolder3.Name = "placeHolder3";
            this.placeHolder3.Size = new System.Drawing.Size(231, 25);
            this.placeHolder3.TabIndex = 3;
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.Color.Gold;
            this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.button1.ForeColor = System.Drawing.Color.White;
            this.button1.Location = new System.Drawing.Point(75, 400);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(216, 27);
            this.button1.TabIndex = 6;
            this.button1.Text = "저장하기";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Form소개서추가
            // 
            this.ClientSize = new System.Drawing.Size(358, 439);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.placeHolder2);
            this.Controls.Add(this.placeHolder3);
            this.Controls.Add(this.placeHolder1);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form소개서추가";
            this.Text = "소개서 작성";
            this.Load += new System.EventHandler(this.Form소개서추가_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private Controls.PlaceHolder placeHolder1;
        private Controls.PlaceHolder placeHolder2;
        private Controls.PlaceHolder placeHolder3;
        private System.Windows.Forms.Button button1;
    }
}
